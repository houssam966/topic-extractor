# Brandwatch Interview Question: Topics

## What's this?

This is a small app that's meant to extract interesting words and phrases from a body of text. However, unfortunately,
it's not very good! What a shame.

## What do you want me to do?

Your task is to improve the topic extraction algorithm.
Depending on your skills and seniority, you may choose different levels of improvement, for example you could:
- return the topics in order of importance (e.g. topics that occur most often are first in the list)
- normalize words (e.g. `Dog`, `dog!` and `dog's` should be treated as a single topic)
- ignore common words (e.g. so `I`, `and` and `the` shouldn't be the most common topics)
- implement a well-known topic detection algorithm
- make the code more concurrent so it's scalable to huge input files
- if you have other ideas for how to improve it, we'd love to see it! These are just suggestions

Notes:
- You don't need to spend ages on this. **_One coding session (2-3 hours) is plenty_**
- We encourage the use of open-source libraries where these are appropriate
- We encourage the use of unit tests, particularly if they help you verify your code works


## How do I submit my work?

When you're done:
- organize your code in sensible git commits with descriptive names. This will help us read and understand your changes
- Zip up the folder (including the `.git` files)
- send it to the member of the Brandwatch recruitment team you are in contact with, along with a short summary of what you've done


## How will my work be evaluated?

When our engineers receive this, we'll be looking at the following things:

- The quality of the output. Are the topics returned any good?
- How clean and readable the code is (including the git commit history, code structure, and code style)
- The efficiency of the code. How well would this scale to processing massive amounts of text?
- Those are the most important things, but we'll also give bonus points for good documentation, good use of open-source libraries and tools, and good tests

Typically, we expect it to compile and run on a Linux environment with Java 11. If your set up is any different, do let
us know!

Good luck, and may the topics be with you.
