#!/usr/bin/env bash

testOutputPath=./build/outputs/kaspresso
if [ ! -d "$testOutputPath" ]; then
  mkdir -p "$testOutputPath"
fi