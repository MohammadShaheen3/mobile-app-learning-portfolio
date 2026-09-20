# Module 4 Analysis — Navigation, ViewModel, StateFlow, and Adaptive Layouts

## Technique examined

The fourth module introduces techniques needed when an application becomes more than a single interactive screen. The My City Guide contains multiple destinations, a ViewModel-backed UI state, navigation arguments, and an adaptive home layout.

## Direct screen switching compared with Navigation Compose

A small prototype could switch screens by storing a variable such as `currentScreen` and using `when` statements. That method reduces dependencies, but navigation behavior quickly becomes difficult to manage when the application needs a back stack, route arguments, deep links, or consistent destination transitions. Navigation Compose formalizes destinations in a navigation graph and provides back-stack behavior through a `NavController`.

The portfolio uses routes for `home`, `category/{category}`, and `detail/{placeId}`. The route carries only the minimum identifier needed by the destination. This is preferable to passing an entire complex object through navigation because the destination can retrieve the authoritative object from the repository or state holder.

## Composable state compared with ViewModel state

Module 2 successfully keeps state inside one composable because the calculator is small and isolated. In Module 4, state participates in navigation and represents selected domain content. A ViewModel is therefore a better boundary. `CityViewModel` owns `MutableStateFlow` privately and exposes an immutable `StateFlow` to the UI. The UI observes state but does not mutate the flow directly. This is a simple implementation of unidirectional data flow: an event calls a ViewModel function, the ViewModel updates state, and Compose renders the resulting state.

The main benefit is separation of responsibilities. Composables focus on layout and user events while the ViewModel manages screen-related state. The trade-off is additional architecture code. For a single static screen this would be unnecessary, but for a multi-screen application the improved structure justifies the cost.

## Adaptive layout strategy

A phone-only implementation could display the same single column at every width. That is simple but wastes space on tablets, foldables, and desktop-style windows. The home screen uses the available width to choose between a compact layout and a two-pane arrangement. The wide version displays categories beside place recommendations so more information is visible without forcing the user through extra navigation.

Using `BoxWithConstraints` keeps the example easy to understand, although production applications may prefer the newer adaptive layout APIs and window size classes described by Android guidance. The important design decision is to respond to available space instead of treating device type as a fixed assumption.

## Relationships between architecture techniques

Navigation, ViewModel state, and adaptive UI solve different problems but work best together. Navigation defines where the user can move; the ViewModel keeps important screen state independent of a specific composable instance; and the adaptive layout determines how the same information should be presented at different widths. Separating these responsibilities reduces coupling. A navigation change does not require rewriting repository data, while an adaptive layout change does not need to change the selected place state.

## Strengths and limitations

The implementation is more maintainable than manually switching composables because destinations, state, and layout responsibilities are explicit. It also demonstrates a path toward larger app architecture. The repository is intentionally in-memory, so process death and persistent data are not handled. A more advanced application could add a data layer backed by Room or a remote API, dependency injection, lifecycle-aware state collection, and automated navigation tests.

## Technical justification

Navigation Compose is justified by the presence of multiple destinations and a back stack. ViewModel and StateFlow are justified because selected domain data should not be owned by a temporary UI function. An adaptive wide layout is included because modern Android applications are expected to work across more than one screen size. These choices demonstrate how architecture becomes progressively more important as application complexity increases.
