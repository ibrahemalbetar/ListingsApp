package com.estarta.listingapp.extensions

fun String.isFloat() = this.toFloatOrNull() != null

fun String.isInt() = this.toIntOrNull() != null

fun String.quoted() = this.replace("\"", "")