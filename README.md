# CDE2313 Mobile Application Development — GitHub Learning Portfolio

**Student:** Mohammad Shaheen  
**Student ID:** `AIU23102321`  
**Programme:** Bachelor of Computer Science (Cyber Security)  
**Course:** CDE2313 Mobile Application Development  
**Academic Session:** 2025/2026  
**Lecturer:** Ts Mohd Zulkifli Mohd Zaki  
**Android Developer Profile:** `ADD-ANDROID-DEVELOPER-PROFILE-URL`  
**GitHub Repository:** https://github.com/MohammadShaheen3/mobile-app-learning-portfolio

## Assessment Overview

This repository is an individual Android learning portfolio organized around four consecutive units from the official **Android Basics with Compose** course. The portfolio combines practical source code, evidence folders, technical analysis, reflection, and version-control history.

The four selected modules create a clear learning progression: foundational Kotlin and Compose, interactive UI and state, lists and Material Design, then navigation and app architecture. This sequence also provides twelve pathway quizzes/badge opportunities, exceeding the assessment minimum of ten badges once the quizzes are completed on the Android Developers learning platform.

## Selected Learning Modules

| Module | Official Unit | Portfolio Application | Main Techniques |
|---|---|---|---|
| 1 | Your first Android app | Business Card | Kotlin basics, composables, layout, text, reusable UI |
| 2 | Building app UI | Tip Calculator | State, user input, events, conditionals, testing |
| 3 | Display lists and use Material Design | Developer Affirmations | Collections, LazyColumn, cards, animation, accessibility |
| 4 | Navigation and app architecture | My City Guide | Navigation Compose, ViewModel, StateFlow, adaptive layout |

## Repository Structure

```text
mobile-app-learning-portfolio/
├── README.md
├── Module-1/
│   ├── Source-Code/BusinessCardApp/
│   ├── Screenshots/
│   ├── Badge-Evidence/
│   ├── Learning-Notes.md
│   └── Analysis.md
├── Module-2/
│   ├── Source-Code/TipCalculatorApp/
│   ├── Screenshots/
│   ├── Badge-Evidence/
│   ├── Learning-Notes.md
│   └── Analysis.md
├── Module-3/
│   ├── Source-Code/AffirmationsApp/
│   ├── Screenshots/
│   ├── Badge-Evidence/
│   ├── Learning-Notes.md
│   └── Analysis.md
├── Module-4/
│   ├── Source-Code/MyCityApp/
│   ├── Screenshots/
│   ├── Badge-Evidence/
│   ├── Learning-Notes.md
│   └── Analysis.md
├── Reflection/Reflection.md
├── References/References.md
├── Submission/Badge-Summary.md
└── RUN_AND_CAPTURE_GUIDE.md
```

## Badge Summary

| # | Badge | Module | Evidence |
|---:|---|---|---|
| 1 | Introduction to Kotlin | 1 | `Module-1/Badge-Evidence/01-programming-in-kotlin.png` |
| 2 | Setup Android Studio | 1 | `Module-1/Badge-Evidence/02-android-studio.png` |
| 3 | Build a basic layout | 1 | `Module-1/Badge-Evidence/03-build-basic-layout.png` |
| 4 | Kotlin fundamentals | 2 | `Module-2/Badge-Evidence/04-kotlin-fundamentals.png` |
| 5 | Add a button to an app | 2 | `Module-2/Badge-Evidence/05-dice-roller.png` |
| 6 | Interacting with UI and state | 2 | `Module-2/Badge-Evidence/06-ui-and-state.png` |
| 7 | More Kotlin fundamentals | 3 | `Module-3/Badge-Evidence/07-more-kotlin-fundamentals.png` |
| 8 | Build a scrollable list | 3 | `Module-3/Badge-Evidence/08-scrollable-list.png` |
| 9 | Build beautiful apps | 3 | `Module-3/Badge-Evidence/09-build-beautiful-apps.png` |
| 10 | Architecture Components | 4 | `Module-4/Badge-Evidence/10-architecture-components.png` |
| 11 | Navigation in Jetpack Compose | 4 | `Module-4/Badge-Evidence/11-navigation-compose.png` |
| 12 | Adapt for different screen sizes | 4 | `Module-4/Badge-Evidence/12-adaptive-layouts.png` |

> Badge image files must be screenshots from the student's own Android Developers profile after completing the relevant quizzes. Do not replace them with mock evidence.

## GitHub Portfolio Overview

The repository is structured so that each module contains implementation code, screenshots, badge evidence, concise learning notes, and a separate analysis. The Android source is configured as a multi-module Gradle project, so the repository root can be opened directly in Android Studio and each application module can be selected and run independently.

## Reflection Summary

Across the four modules, the implementation moves from simple declarative UI toward state-driven and architecture-aware applications. The most important progression is the separation of concerns: early code can keep logic close to the composable because the screen is simple, while later modules benefit from a ViewModel, explicit UI state, navigation boundaries, and adaptive layouts. The portfolio therefore demonstrates not only how individual Android APIs are used, but why different techniques become appropriate as application complexity increases.

## AI Use Disclosure

AI assistance was used to support code drafting, documentation structure, language refinement, and debugging during development of this portfolio. The final repository should be reviewed, run, and understood by the student before submission, and all badge/profile/screenshots must be authentic evidence from the student’s own account and Android Studio environment.

## Official Learning Source

Android Developers. *Android Basics with Compose*. https://developer.android.com/courses/android-basics-compose/course

See `References/References.md` for the APA-style reference list.