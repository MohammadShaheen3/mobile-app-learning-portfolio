# Module 2 Learning Notes — Building App UI

## Key concepts

- `rememberSaveable` stores UI state that should survive common recreation scenarios.
- Input values from text fields arrive as strings and must be validated before numeric use.
- UI state should be a source of truth; derived values can be calculated from that state.
- Event handlers such as `onValueChange`, `onCheckedChange`, and `onClick` update state.
- Recomposition updates affected UI when observed state changes.
- Pure calculation functions are easier to unit-test than logic embedded directly in UI code.

## Practical implementation

The Tip Calculator separates calculation logic into `calculateTip()` and keeps input state inside `TipCalculatorScreen()`. Invalid numeric input is handled with `toDoubleOrNull()` rather than allowing a parsing exception. Two JUnit tests verify the calculation logic.

## Evidence to capture

1. Initial Tip Calculator screen.
2. Screen after entering an amount and changing tip percentage.
3. Screen with “Round up tip” enabled.
4. Unit test result in Android Studio.
5. Three Unit 2 badge screenshots.
