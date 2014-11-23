@echo off
set class=fr.isima.webservice.BiblioService
set clpth=./war/WEB-INF/classes
set resourcedir=./war
set outsourcedir=./src
set outdir=./war/WEB-INF/classes
@echo on
wsgen -verbose -keep -cp "%clpth%" -wsdl -r "%resourcedir%" -d "%outdir%" -s "%outsourcedir%" %class%