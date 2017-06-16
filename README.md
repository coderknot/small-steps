# Small Steps

#### _An Android app focusing on habit building through small steps, Current Version: 06-14-2017_

#### By _**John Carr**_

## Description
Small Steps is an Android App that aims to help you build habits by focusing on small steps. It is based on the ideas of The 3 R’s of Habit Change: Reminder, Routine, and Reward:

* A Reminder is the trigger that initiates the behavior.
* A Routine is the behavior, itself.
* A Reward is the benefit gained from performing the behavior.

This app uses Reminders to trigger Routines to earn Rewards. With this process, small steps build big habits!

![Small Steps: Main Activity](/app/src/main/assets/images/MainActivity.jpg?raw=true "Small Steps - Main Activity")
![Small Steps: Habits Activity](/app/src/main/assets/images/HabitsActivity.jpg?raw=true "Small Steps - Habits Activity")
![Small Steps: Group List Activity](/app/src/main/assets/images/GroupListActivity.jpg?raw=true "Small Steps - Group List Activity")

## Setup / Installation Requirements

### Prerequisites

You will need the following things properly installed on your computer:

* [Java SDK 1.8 Update 121 +](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
* [Android Studio 2.3.2 +](https://developer.android.com/studio/index.html)

### Installation

* `git clone <repository-url>` this repository
* Open this project in Android studio
* Run in AVD emulator

## Planning

### Specifications
| Behavior | Example Input | Example Output |
|----------|---------------|----------------|
| Users can view a list of habits/steps | User navigates to Habits Activity | Habits List is displayed |
| Users can add habits/steps | User enters Habit name | Habit is added to list |

### Known Issues/Bugs

| Issue No. | Date | Issue | Notes |
|-----------|------|-------|-------|
| 1.001 | 06/14/2017 | If habits are created AND deleted, app DOES crash upon exiting Habit List Activity | if habits are created AND sorted, app DOES NOT crash on exit |

### TO-DO
* Fix habit list issue (Issue No. 1.001)
* Layouts:
  * Vertical
  * Landscape
  * Possible "Back" button in layout, instead of app bar?
* Update README:
  * New Screenshots
  * Planning and Specifications


## Technologies Used
* _Android_
* _Java_
* _XML_

## Acknowledgements
[ButterKnife](https://github.com/JakeWharton/butterknife)

## Support and contact details
Questions? Concerns? Suggestions?

Reach out to me via github:
<http://github.com/coderknot>

## License

MIT License

__Copyright (c) 2017 John Carr__

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
