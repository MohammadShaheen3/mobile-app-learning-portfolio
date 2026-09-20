# Module 4 Learning Notes — Navigation and App Architecture

## Key concepts

- Navigation Compose provides a navigation graph for multiple composable destinations.
- Route arguments allow a destination to identify the content it should display.
- A `ViewModel` keeps screen-related state separate from composable implementation details.
- `StateFlow` represents observable state that can be collected by Compose.
- Unidirectional data flow makes state changes easier to trace.
- Adaptive layouts should respond to available window space rather than assuming one phone size.

## Practical implementation

The My City Guide contains home, category, and detail destinations. A `CityViewModel` exposes `CityUiState` through `StateFlow`. The home screen uses `BoxWithConstraints` to switch between a compact single-column layout and a wider two-pane layout.

## Evidence to capture

1. Compact home screen.
2. Category screen.
3. Detail screen after navigation.
4. Wide-screen/tablet layout showing two panes.
5. Three Unit 4 badge screenshots.
