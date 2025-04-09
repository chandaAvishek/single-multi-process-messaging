#!/bin/bash
mvn clean package

# Single Process
mvn exec:java -Dexec.mainClass="com.assignment.SingleProcessApp"

# Multi Process
mvn exec:java -Dexec.mainClass="com.assignment.MultiProcessApp" -Dexec.args="responder" &
sleep 2
mvn exec:java -Dexec.mainClass="com.assignment.MultiProcessApp" -Dexec.args="initiator"
