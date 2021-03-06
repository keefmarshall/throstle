Hypophyseal Throstle
====================

The Throstle is a semantic password generator written in Kotlin, using English language words and phrases
from WordNet to generate memorable passwords that will pass most stringent requirements.

Disclaimers apply: any password generated from word lists will be significantly less secure than a
purely random one.

This was the first thing I wrote in Kotlin, and I can already see places where I've made errors
or could have done considerably better - don't judge too harshly!

Building
--------

I have spent very little time on the build mechanism - I have been working in IntelliJ Idea and I use
this to build the throstle.jar executable file which can be run, a bit like a Node.js Express app,
to start a web service listening on port 3000.

I've included the .idea files in this repo.

You will also need the [wasabi](https://github.com/wasabifx/wasabi) library loaded as an
additional module into your Idea session and added as a dependency.
This seems to be a painful process for reasons I am unclear about.

Word lists
----------

There are four word files required - adj, nouns, advs, verbs.

These are simple text files with one word per line. I extracted my versions from WordNet 3.1
using Unix command line tools:

e.g.:

```text
tail +30 data.adv | cut -d " " -f 5 | sed s/\(.*\)// | grep -v '^[^A-Za-z]' | sort -f | uniq > advs.krm
```

Deployment
----------

The service is deployed at [https://throstle.eleusis.uk](https://throstle.eleusis.uk) for general use.
