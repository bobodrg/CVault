# Prototype reflection: where AI stopped

> Draft written with AI from the stress tests run on 2026-10-25 (emulator: Medium Phone, API 37). Review it, and write the last section yourself.

## What AI did well

- **Screens and layout:** the Matches list (cards, a colour-coded score badge) and the detail screen (skill chips, status chips, note field) came out working on the first build.
- **Fake data:** six realistic postings, with scores and matched skills that fit the CVault idea.
- **Navigation boilerplate:** type-safe Navigation Compose routes (`@Serializable` `Matches` and `PostingDetail(postingId)`), including the Gradle version catalog entries.
- **Tooling details I would have lost time on:** Material3 no longer brings in the icons library, so `material-icons-core` had to be added for the back arrow. Type-safe routes need the Kotlin serialization plugin.

## What broke when you stressed it

- **Rotate the device:** on the detail screen I set the status to *Applied* and typed a note, then rotated. **Both were lost**: the status went back to *New* and the note was empty. The screen itself survived (still on the same posting), because Navigation saves the back stack. Status and note live in plain `remember`, which only lasts as long as the composition, and rotation recreates the Activity.
- **Kill and reopen the app:**
  - *Process death* (app in the background, process killed, then reopened): Android restored the **detail screen** from saved state, but the status was back to *New*. The screen restores, the data doesn't, which is the worst mix for a user.
  - *Force-stop*: the app starts from zero on the Matches list. Nothing the user entered is saved anywhere.
- **Airplane mode:** everything works, but **only because the app has no network code at all**. The data is hardcoded. This says nothing about real offline behaviour: once postings come from an API, the app will show nothing offline unless they are cached locally.
- **500 items in a list:** with 500 generated postings, a debug build on the emulator rendered **94% janky frames** (median frame 65 ms, 90th percentile 125 ms, 98 slow UI-thread frames out of 125). Debug Compose builds are much slower than release builds, so I tried a release build to compare, and **the release build crashed at startup** (an R8 `IllegalAccessError` in `ProcessLifecycleInitializer`). So I can't yet say whether the list is smooth in production. Also, the list is re-sorted inside the composable, which should move out of the UI.
- **Two people editing the same data:** not possible. The data exists only in memory on one phone. There is no server, no user identity, and no notion of sync or conflict. For CVault this is mostly fine (one person's job search), but the same user on two devices (phone + tablet) would see different statuses.

## What is missing for production

| Gap in the prototype | Lab that fixes it |
|---|---|
| No separation of UI and logic; state resets | Lab 4 (architecture) |
| No data from a real server | Lab 5 (networking) |
| Nothing is saved on the device | Lab 6 (persistence) |
| Does not work offline; no background sync | Lab 7 (offline-first, WorkManager) |
| Dependencies wired by hand | Lab 8 (dependency injection) |
| No tests; nothing stops a broken change | Lab 9 (testing, CI) |
| No mobile-computing concept beyond a UI | Lab 10 (integrated concept) |

Gaps specific to CVault:

| Gap | Lab that fixes it |
|---|---|
| Status and note are lost on rotation and process death | Lab 4 (ViewModel + SavedStateHandle), Lab 6 (Room) |
| Sorting happens inside the composable on every recomposition | Lab 4 (the ViewModel exposes an already-sorted state) |
| Match scores are hardcoded, not computed from a real CV | Lab 10 (on-device text embeddings) |
| No way to import or paste a CV | Lab 4/6 (a new screen, CV stored locally) |
| No morning refresh and no "N new matches" notification (POST_NOTIFICATIONS) | Lab 7 |
| Release build crashes at startup (R8 `IllegalAccessError`) | Lab 9 (a CI build of the release variant would catch it) |
| List performance unknown in release; janky in debug | Re-measure once the release build works |

## One sentence

_TODO (Bogdan, in your own words): what is the difference between what AI gave you today and a production app, and why can AI not simply close that gap for you?_
