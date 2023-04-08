package com.challenge.acronyms.utils

class NullResponse(message: String = "The response is null"): Exception(message)
class FailureResponse(message: String?): Exception(message)