@echo off
IF NOT EXIST "miniMatador.jar" (
	javac main/java/*.java main/java/models/*.java main/java/controllers/*.java main/java/ui/*.java -cp main/java/matadorgui-3.1.6.jar
	jar cmf main/java/META-INF/MANIFEST.MF miniMatador.jar main/java/*
)
start "" "javaw -jar miniMatador.jar"