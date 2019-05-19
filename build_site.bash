#!/usr/bin/env bash

docker run -v $PWD:/antora --rm -t antora/antora site.yml
