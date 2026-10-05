# Learning Java and FTC programming

Start with the [Java primer](java-primer/java_primer.html), then the [tank-drive workshop](workshop/README.md), or use the [simulator lessons](simulator/README.md) without a physical robot.

## Project organization

- `education/`: teaching documents, PDFs, and diagrams.
- `TeamCode/src/main/java/org/firstinspires/ftc/teamcode/education/`: educational Java and lesson guides beside their examples.
- `TeamCode/src/main/java/org/firstinspires/ftc/teamcode/teamcode/`: current robot OpModes, mechanisms, diagnostics, and the existing previous-season archive.

Competition code must not import educational code. Standalone lessons must not depend on current-season mechanisms. A robot-specific diagnostic stays with competition code even when it is useful for teaching. `teamcode/test/MecanumSimple.java` stays there because it exercises the current robot through `MecanumDrive`; its existing Driver Station group is preserved.

Educational Java still compiles as part of TeamCode. Annotated educational OpModes have `@Disabled` so they do not appear on the Driver Station by default. In a supervised workshop, remove it from the example you are working on, then restore it afterward. A folder or group name alone does not disable an OpMode.

## Consolidation record

[Source-to-destination inventory](consolidation-map.json) records the source commit and destination of every imported or moved educational asset. The BioBuzz workshop guide is retained because it contains newer package-navigation corrections than Workshop/master. Other workshop documents match across those branches. The Workshop tank-drive solution is included alongside the starter. Simulator solutions retain their original class names and control logic; package names and guide references reflect this layout. The DECODE example has one SDK 12 compatibility adjustment: ID telemetry uses `AprilTagSingleDetection`, since the common `AprilTagDetection` type now also represents clusters and has no `id` field.

The VRS strafing guide's original screenshot and earlier VRS lessons were absent from the source branch; those unavailable references are now plain text. Educational branches are retained until integration and runtime checks are complete.
