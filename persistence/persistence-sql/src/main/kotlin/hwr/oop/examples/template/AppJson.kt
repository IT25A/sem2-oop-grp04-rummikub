package hwr.oop.examples.template

import kotlinx.serialization.json.Json

internal val AppJson = Json {
    prettyPrint = true
    ignoreUnknownKeys = true
    encodeDefaults = true
}