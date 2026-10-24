# App idea

> Draft written with AI on 2026-10-24. Review every answer: these are defended in the project spot-check.

## The basics

- **App name:** CVault
- **One-line pitch:** A job radar for active job seekers that checks new postings every morning and ranks them against your CV, with the matching done entirely on your phone so your CV never leaves it.
- **The problem:** Job boards bury you in irrelevant postings. To get "smart" matching you have to upload your CV to every platform, and those platforms keep, profile and resell it.
- **Target users:** Students, juniors and career switchers who check jobs on the commute or between classes, often on patchy mobile data or none at all (the metro).

## The prototype scope

Keep this tiny: one or two screens for Lab 3.

- **Core screen(s):**
  1. **Matches:** today's postings, sorted by match score against your CV (score badge, title, company, location/remote).
  2. **Posting detail:** full description, match score, the skills that overlapped with your CV, a save button, application status and a personal note.
- **The main "thing" the app manages:** a job posting, and the user's application to it.
- **A few fields of that thing:** id, title, company, company logo URL, location / remote, tags (skills), description, source URL, published date, source (Remotive / Arbeitnow), match score, saved, status (new / saved / applied / interviewing / rejected), note.

## The data

- **What records does the app create or collect?** Fetched job postings with their match scores; the user's CV (pasted text or an imported file) and its embedding; saved postings, application status and notes.
- **Where does data come from?** Postings come from public job APIs (Remotive, Arbeitnow). The CV, statuses and notes come from user input. The match scores come from an on-device model.
- **Where does data need to go?** Nowhere. Everything stays on the device. Only plain requests for public job feeds go out, and they carry no CV data.

## Mobile-computing framing (required)

- **What must persist on the device** (survive a restart)?
  The CV and its embedding, the cached postings with their scores, saved postings, application statuses and notes, and the time of the last sync.
- **What crosses the network** (comes from or goes to a server or API)?
  Incoming only: job postings from the public APIs, and company logos (loaded with Coil). Nothing about the user is uploaded. We fetch broad category feeds rather than queries built from the CV, so even the requests reveal nothing.
- **What must still work with no signal**, and how should it behave offline?
  Browsing the last synced matches, reading full descriptions, and editing statuses and notes all work offline. A banner shows "Last updated X hours ago". Refreshing is deferred until the network returns: a WorkManager job runs on an unmetered network and posts a "N new matches" notification.
- **The placement question:** CV-to-posting matching. Turning text into embeddings and computing their similarity could run on-device (MediaPipe Text Embedder) or in the cloud (an embedding API).
  - **On-device (chosen):** *privacy*: the CV never leaves the phone, which is the point of the app. *Offline*: postings can be re-ranked without a network. *Cost*: no per-request API bill.
  - **What we trade:** *energy*: embedding a few hundred postings each morning costs battery (mitigation: batch it in WorkManager while charging). *Size*: the model adds a few MB to the app. *Quality*: a small on-device model matches less precisely than a large cloud model. *Latency*: per posting it is fast, but a whole batch on an old phone is noticeable.
  - **What would change our mind:** if the volume of postings grows a lot, a hybrid makes sense. Postings are public data, so a server could embed them once for everyone, and only the CV embedding has to stay local.

## What comes next

See `reflection.md` once the prototype is built and stress-tested.
