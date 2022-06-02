#!/bin/bash
for i in {1..100}
do
   cat quarkus-log/quarkus$i.log | grep -o 'started in [0-9\.]*.' | cut -c 12-16 >> results.txt
done
