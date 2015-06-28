#!/bin/bash

PARSER_ROOT="lib/TweeboParser"

filename="file_${RANDOM}"

echo $@ > ${filename}

./${PARSER_ROOT}/run.sh ${filename} > /dev/null 2> /dev/null

mkdir -p tmp
mv ${PARSER_ROOT}/${filename}.predict tmp/${filename}.predict

rm -f $filename

echo tmp/${filename}.predict
