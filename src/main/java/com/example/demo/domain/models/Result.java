package com.example.demo.domain.models;

import com.example.demo.domain.enums.ResultType;

public record Result(ResultType resultType, String message) { }
