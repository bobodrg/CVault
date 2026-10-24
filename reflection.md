# Prototype reflection: where AI stopped

Fill this in after Part 4, once the prototype has been stress-tested. Be honest and specific; a vague "it works fine" means it was not pushed hard enough.

## What AI did well

Where did AI genuinely accelerate you? (screens, sample data, navigation, boilerplate, ...)

## What broke when you stressed it

- **Rotate the device:** (did state survive?)
- **Kill and reopen the app:** (was anything saved?)
- **Airplane mode:** (graceful, blank, or crash?)
- **500 items in a list:** (still smooth?)
- **Two people editing the same data:** (any sync or conflict handling?)

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
| Match scores are hardcoded, not computed from a real CV | Lab 10 (on-device embeddings) |
| No way to import a CV | |
| No "N new matches" notification / POST_NOTIFICATIONS permission | Lab 7 |

## One sentence

In your own words: what is the difference between what AI gave you today and a production app, and why can AI not simply close that gap for you?
