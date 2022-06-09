#!/bin/bash
framework=$1
for i in {1..100}
do
   if [ $framework == 'quarkus' ]
   then
        mvn clean package -Dquarkus.package.type=uber-jar > tmp.log
   else
        mvn clean package > tmp.log
   fi
   cat tmp.log | grep -o 'Total time:  [0-9\.]*.' | cut -c 14-18 >> $framework-result.txt
   rm -f tmp.log
done
