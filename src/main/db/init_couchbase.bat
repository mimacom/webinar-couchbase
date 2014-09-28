@echo off

couchbase-cli bucket-delete -u %1 -p %2 -c %3 --bucket=%4
if errorlevel 1 echo The bucket does not exist, creating it...

couchbase-cli bucket-delete -u %1 -p %2 -c %3 --bucket=test-polls
if errorlevel 1 echo The bucket does not exist, creating it...

couchbase-cli bucket-create -u %1 -p %2 -c %3 --bucket=%4 --bucket-type=couchbase --bucket-ramsize=%5 --enable-flush=1
couchbase-cli bucket-create -u %1 -p %2 -c %3 --bucket=test-polls --bucket-password=test-polls --bucket-type=couchbase --bucket-ramsize=%5 --enable-flush=1