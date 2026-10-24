# AI-use log

Append-only. Each member keeps their own section. One row per meaningful AI interaction: anything where the model produced code or a design decision that was accepted or rejected. Trivial autocompletions are not logged.

The point is to show that we **own** what we submitted: we understood what the model produced, judged it, and decided what to keep.

## Bogdan Dragomir

| Date | What I was trying to do | Tool + prompt (short) | What the model produced | What I accepted / rejected / changed, and why | How I verified it |
|---|---|---|---|---|---|
| 2026-10-24 | Understand the project requirements | Claude Code: "analyze Proiect MC.md" and the project handout PDF | A summary of the Lab 3 brief and the handout: stack, floor, milestones, grading | Accepted as a reading aid; the originals remain the source of truth | Re-read both documents |
| 2026-10-24 | Find an app idea that fits the four Lab 3 criteria | Claude Code: asked for app ideas, then more "everyday, niche" ones, then more digest-style ones | About 30 ideas over several rounds, each checked against the criteria | Chose the job radar. Rejected "local events digest" because an app with that name already exists | _TODO: your own reasons for the choice_ |
| 2026-10-24 | Name the app | Claude Code: "give me more names for the job radar" | About 40 names grouped by theme | Chose **CVault** because it states the placement decision (the CV stays on the device) | _TODO_ |
| 2026-10-24 | Draft `app-idea.md` | Claude Code wrote a first draft of every section | A filled-in draft, including the placement trade-offs | _TODO: review every answer, keep/change, note why_ | _TODO_ |
| 2026-10-25 | Add type-safe navigation | Claude Code: add Navigation Compose with @Serializable routes | Version catalog entries (navigation-compose 2.10.2, the Kotlin serialization plugin, material-icons-core) and the `Matches` / `PostingDetail(postingId)` routes in `CVaultNavHost.kt` | _TODO_ (note: material3 no longer pulls in the icons library, so it had to be added for the back arrow) | `./gradlew assembleDebug` passes; tapping a card opens the detail screen on the emulator |
| 2026-10-25 | Prototype data | Claude Code: a `JobPosting` model and 6 fake postings | `data/JobPosting.kt`, `data/FakeJobs.kt` | _TODO_ | Read the fields against `app-idea.md` |
| 2026-10-25 | Prototype screens | Claude Code: a Matches list sorted by score, plus a Posting detail screen with status chips and a note | `MatchesScreen.kt` (LazyColumn of cards, `ScoreBadge`), `PostingDetailScreen.kt` (matched skills, description, status, note) | Changed: removed a duplicated "Remote ·" prefix on cards. Kept plain `remember` for status/note on purpose, so the rotation test exposes the gap Lab 4 fixes. _TODO: your own judgement_ | Ran on the Medium Phone API 37 emulator; screenshots of both screens |
| 2026-10-25 | Part 4 stress tests | Claude Code: drive the emulator with adb (rotate, kill, airplane mode, 500 items) and draft `reflection.md` | Screenshots before and after each test, frame stats from `dumpsys gfxinfo`, a drafted `reflection.md` | Its first process-death attempt (`am kill`) didn't actually kill the app; it noticed and switched to `run-as … kill`. The 500-item test used a temporary code change that was reverted, not committed. _TODO: your own judgement_ | Screenshots of each state; `git status` clean after the revert |
| 2026-10-25 | CI gate for M1 | Claude Code: add a minimal GitHub Actions workflow (build + unit tests) | `.github/workflows/ci.yml`: checkout, JDK 25 (matches `gradle-daemon-jvm.properties`), setup-gradle, `assembleDebug`, `testDebugUnitTest`, test reports uploaded on failure | _TODO_ | Same tasks pass locally (`BUILD SUCCESSFUL`); _TODO: confirm the CI check is green on PR #1_ |

## Prompts worth remembering

-

## One thing AI got wrong, or could not tell us

_To write at the end of the project._
