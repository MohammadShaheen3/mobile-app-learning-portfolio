# Module 2 Analysis — State, User Interaction, and Testable Logic

## Technique examined

The second module introduces interactive Compose UI. The Tip Calculator accepts a bill amount and tip percentage, lets the user decide whether to round the result, and recalculates the displayed tip when state changes.

## Local state compared with derived values

One possible design is to store every displayed value as mutable state. For example, the app could store the amount, percentage, round-up choice, and calculated tip independently. This appears straightforward, but it creates synchronization risk because the stored tip can become inconsistent with the inputs. The portfolio instead stores only the user-controlled inputs and derives the tip from them. This reduces the number of mutable values and makes the current screen output a predictable function of the current inputs.

`rememberSaveable` is used instead of plain `remember` for the form values. `remember` is useful for state that only needs to survive recomposition, while `rememberSaveable` is more appropriate for simple user-entered values because it can restore supported values across activity recreation. The trade-off is that not every complex object is automatically saveable, so larger applications often move durable screen state into a ViewModel rather than relying on composable-local storage.

## Input validation and error resistance

Directly calling `toDouble()` on text input can throw an exception when the field is empty or contains an incomplete number. Using `toDoubleOrNull()` turns invalid input into a controlled `null` result and allows the UI to fall back to zero. This approach improves robustness without requiring exception handling for routine user input. A production application could improve the experience further by displaying validation feedback, but the current strategy is suitable for the learning objective and keeps the state flow visible.

## Separation of calculation logic

The tip calculation is placed in a pure `calculateTip()` function rather than embedded inside the composable. This makes the behavior easier to reason about and allows it to be tested with ordinary JVM unit tests. The UI is responsible for gathering input and presenting output, while the function is responsible only for calculation. This separation is a small-scale example of the broader architectural separation used later in Module 4.

## Strengths and limitations

The approach is concise and responsive because Compose automatically re-executes the relevant UI when state changes. It also avoids manual view lookup and explicit UI refresh calls. Its main limitation is that the screen still owns its business state. That is reasonable for a small calculator, but it would become less suitable if the app needed persistence, multiple screens, asynchronous work, or shared state. At that point, moving state and business rules into a ViewModel would improve testability and lifecycle handling.

## Technical justification

Local state is deliberately retained in the composable because the calculator has one screen and no shared or long-lived domain state. A separate pure function is used for the calculation because that logic benefits directly from independent testing. This mixed approach keeps the architecture proportional to the problem: local UI state remains local, while reusable business logic is extracted and tested.
