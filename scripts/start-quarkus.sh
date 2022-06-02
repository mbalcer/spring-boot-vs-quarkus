#!/bin/bash
for i in {1..100}
do
   java -jar csv-json-converter-quarkus/target/csv-json-converter-quarkus-1.0-SNAPSHOT-runner.jar > quarkus-log/quarkus$i.log 2>&1 
done
