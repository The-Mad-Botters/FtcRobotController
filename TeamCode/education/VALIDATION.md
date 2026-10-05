# Reorganization validation

Branch: `reorganize-folders-2026`.
Baseline: `ee6f160` on `2026-2027_BioBuzz`, committing all existing tracked work before the reorganization.
Remote refs were refreshed before importing material.

## Completed checks

- Baseline app assembly passed with Java 17.
- Final clean TeamCode app assembly passed with Java 17 and FTC SDK 12.0.0:

```sh
JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk-17.jdk/Contents/Home ./gradlew :TeamCode:clean :TeamCode:assembleDebug --console=plain
```

Use your installed Java 17 location if different. No SDK/dependency/Gradle configuration changes were required for this reorganization.

- All 49 source-to-destination mappings resolve: 43 SimulatorLessons assets, 5 BioBuzz workshop assets, and the completed tank-drive example from Workshop.
- The three PDF/PNG assets are byte-identical to their source commits. The Java primer HTML is unchanged.
- All 45 local references in the educational Markdown/HTML resolve with exact case.
- All 46 TeamCode Java package declarations match their directories; no duplicate fully qualified class names were found.
- Competition sources under `teamcode` and build configuration are unchanged from the checkpoint.
- No educational imports of current-season code, or competition imports of educational code, were found.
- All four annotated educational OpModes include `@Disabled`; other simulator examples have no registration annotation.
- Both relocated HTML guides rendered in headless Chrome at 1280px with no broken images or JavaScript page errors. Screenshots were inspected for retained document layout.
- Independent read-only review verified asset coverage, packages, links, and unchanged competition sources. Its filename-label and activation-snippet findings were corrected.

The build still reports deprecated SDK usage and Gradle features, plus native libraries packaged without symbol stripping. These do not prevent assembly.

## Remaining runtime and integration checks

- On the robot, check the Driver Station OpMode list and smoke-test competition hardware initialization and affected competition OpModes.
- In the appropriate simulator, paste/run representative movement, sensor, VRS strafing, and DECODE field examples with the editor's required class/package adaptation.
- Integrate this branch into BioBuzz and decide how the same education tree is maintained on `master`.
- Before retiring Workshop and SimulatorLessons, preserve their tips with tags and verify integrated asset coverage again.

No robot deployment, live simulator execution, branch merge, remote push, or source-branch deletion was performed as part of this reorganization.
