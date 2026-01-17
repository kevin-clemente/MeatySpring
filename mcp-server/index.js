#!/usr/bin/env node

import { Server } from "@modelcontextprotocol/sdk/server/index.js";
import { StdioServerTransport } from "@modelcontextprotocol/sdk/server/stdio.js";
import {
  CallToolRequestSchema,
  ListToolsRequestSchema,
} from "@modelcontextprotocol/sdk/types.js";

const BASE_URL = process.env.MEATY_API_URL || "http://localhost:8080";

// Helper function to make HTTP requests
async function makeRequest(path, options = {}) {
  const url = `${BASE_URL}${path}`;
  try {
    const response = await fetch(url, {
      headers: {
        "Content-Type": "application/json",
        ...options.headers,
      },
      ...options,
    });

    if (!response.ok) {
      const errorText = await response.text();
      throw new Error(`HTTP ${response.status}: ${errorText}`);
    }

    const text = await response.text();
    return text ? JSON.parse(text) : { success: true };
  } catch (error) {
    if (error.cause?.code === "ECONNREFUSED") {
      throw new Error(`Cannot connect to Meaty API at ${url}. Is the server running?`);
    }
    throw error;
  }
}

// Create MCP server
const server = new Server(
  {
    name: "meaty-api",
    version: "1.0.0",
  },
  {
    capabilities: {
      tools: {},
    },
  }
);

// Define available tools
server.setRequestHandler(ListToolsRequestSchema, async () => {
  return {
    tools: [
      {
        name: "get_all_recipes",
        description: "Get all recipes from the Meaty database with their ingredients",
        inputSchema: {
          type: "object",
          properties: {},
          required: [],
        },
      },
      {
        name: "create_recipe",
        description: "Create a new recipe in the Meaty database",
        inputSchema: {
          type: "object",
          properties: {
            userId: {
              type: "integer",
              description: "The user ID creating the recipe",
            },
            recipeName: {
              type: "string",
              description: "Name of the recipe",
            },
            description: {
              type: "string",
              description: "Description of the recipe",
            },
            imagemUrl: {
              type: "string",
              description: "URL of the recipe image",
            },
          },
          required: ["userId", "recipeName"],
        },
      },
      {
        name: "get_all_types",
        description: "Get all ingredient types (categories like 'carne', 'peixe', 'especiaria')",
        inputSchema: {
          type: "object",
          properties: {},
          required: [],
        },
      },
      {
        name: "get_all_ingredients",
        description: "Get all ingredients from the Meaty database",
        inputSchema: {
          type: "object",
          properties: {},
          required: [],
        },
      },
      {
        name: "create_ingredient",
        description: "Create a new ingredient in the Meaty database",
        inputSchema: {
          type: "object",
          properties: {
            ingredientName: {
              type: "string",
              description: "Name of the ingredient",
            },
            type: {
              type: "object",
              description: "The type/category of the ingredient",
              properties: {
                typeId: {
                  type: "integer",
                  description: "ID of the existing type",
                },
              },
              required: ["typeId"],
            },
          },
          required: ["ingredientName", "type"],
        },
      },
    ],
  };
});

// Handle tool calls
server.setRequestHandler(CallToolRequestSchema, async (request) => {
  const { name, arguments: args } = request.params;

  try {
    switch (name) {
      case "get_all_recipes": {
        const recipes = await makeRequest("/meaty/getAllRecipes");
        return {
          content: [
            {
              type: "text",
              text: JSON.stringify(recipes, null, 2),
            },
          ],
        };
      }

      case "create_recipe": {
        const { userId, recipeName, description, imagemUrl } = args;
        const recipe = await makeRequest("/meaty/createRecipe", {
          method: "POST",
          headers: { userId: String(userId) },
          body: JSON.stringify({
            recipeName,
            description: description || "",
            imagemUrl: imagemUrl || "",
          }),
        });
        return {
          content: [
            {
              type: "text",
              text: JSON.stringify(recipe, null, 2),
            },
          ],
        };
      }

      case "get_all_types": {
        const types = await makeRequest("/type/all");
        return {
          content: [
            {
              type: "text",
              text: JSON.stringify(types, null, 2),
            },
          ],
        };
      }

      case "get_all_ingredients": {
        const ingredients = await makeRequest("/ingredientes/all");
        return {
          content: [
            {
              type: "text",
              text: JSON.stringify(ingredients, null, 2),
            },
          ],
        };
      }

      case "create_ingredient": {
        const { ingredientName, type } = args;
        const ingredient = await makeRequest("/ingredientes/create", {
          method: "POST",
          body: JSON.stringify({ ingredientName, type }),
        });
        return {
          content: [
            {
              type: "text",
              text: JSON.stringify(ingredient, null, 2),
            },
          ],
        };
      }

      default:
        throw new Error(`Unknown tool: ${name}`);
    }
  } catch (error) {
    return {
      content: [
        {
          type: "text",
          text: `Error: ${error.message}`,
        },
      ],
      isError: true,
    };
  }
});

// Start the server
async function main() {
  const transport = new StdioServerTransport();
  await server.connect(transport);
  console.error("Meaty MCP server running on stdio");
}

main().catch((error) => {
  console.error("Fatal error:", error);
  process.exit(1);
});
