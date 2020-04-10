# MasterMind

Java Applet that guesses the right solution in the [Mastermind game](https://en.wikipedia.org/wiki/Mastermind_(board_game)) with a minimal number of steps by choosing guesses that maximize [informational entropy](https://en.wikipedia.org/wiki/Entropy_(information_theory)).

Click [here](https://andras.niedermayer.ch/software/MasterMindCheerpJ/MasterMindSolver.html) to try.

To modify the code and run it in your web browser, you need to use [CheerpJ](https://leaningtech.com/pages/cheerpj.html) to compile the Java Applet code to JavaScript. To do this, install CheerpJ and the JDK. Then start a Unix terminal or a Windows PowerShell, change to the directory containing the code and
```shell
jar cvf MasterMind.jar *.class
python "~/cheerpj_2.1/cheerpjfy.py" ./app/MasterMind.jar
```
replace `~/cheerpj_2.1/` with the path where you installed CheerpJ.

To test the code locally, you have to run a local web server since CheerpJ refuses to run applets on the local file system. You can use e.g. Python's SimpleHTTPServer. Install Python 3, start a terminal or PowerShell, change to the directory of the code and type
```shell
python -m http.server 8000 --bind 127.0.0.1
```
Then point your browser to the [file on your local web server](http:/127.0.0.1:8000/MasterMindeSolver.html).

Further information is provided [here](http://andras.niedermayer.ch/provided-software/master-mind-solver/).
