# Module 1 Learning Notes — Your First Android App

## Key concepts

- Kotlin is the primary language used in this portfolio.
- A composable function describes UI declaratively using the `@Composable` annotation.
- `Column` arranges children vertically; `Row` arranges them horizontally.
- `Modifier` controls layout, spacing, size, semantics, and interaction.
- `MaterialTheme` supplies consistent typography and component defaults.
- Compose previews help inspect UI without manually navigating through the whole app.
- Reusable composables reduce duplication and make UI structure easier to understand.

## Practical implementation

The Business Card application uses a reusable `ContactRow()` composable instead of repeating the same row structure for each piece of contact information. This keeps the screen readable and demonstrates basic component reuse.

## Evidence to capture

1. App running on emulator/device.
2. Android Studio project view showing `MainActivity.kt`.
3. Compose preview or emulator output.
4. Three Unit 1 badge screenshots.
