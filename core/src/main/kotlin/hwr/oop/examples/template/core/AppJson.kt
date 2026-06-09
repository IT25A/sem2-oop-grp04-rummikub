package hwr.oop.examples.template.core

import kotlinx.serialization.json.Json

        val AppJson = Json {
            prettyPrint = true
            ignoreUnknownKeys = true
            encodeDefaults = true
        }