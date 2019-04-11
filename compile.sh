#!/bin/sh

mvn clean install

mv target/SpigotPlugin-1.8.8-R0.1-SNAPSHOT.jar /srv/paper/plugins/SpigotPlugin.jar

echo Ok
