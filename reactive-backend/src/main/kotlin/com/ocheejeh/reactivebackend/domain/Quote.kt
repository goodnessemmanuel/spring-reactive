package com.ocheejeh.reactivebackend.domain

import org.springframework.data.mongodb.core.mapping.Document
import java.io.Serializable

@Document(value = "quote")
data class Quote (
          val bookId :String,
          val book :String,
          val content :String
     ) :Serializable {
    constructor() : this("", "", "")
 }