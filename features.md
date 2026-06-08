# BeautyTips - Ultimate Growth & Engagement Features Backlog

This document tracks all zero-budget features designed to maximize user retention, virality, and organic downloads. Completed features are checked off below.

---

## 🎮 Category 1: Gamification & Engagement (Habit Building)

### [ ] Feature 1: 7-Day / 14-Day Beauty Challenges
* **Goal:** Increase daily active usage (DAUs) by setting up structured, multi-day self-care routines.
* **Details:** Users join challenges (e.g. *"7-Day Glowing Skin Challenge"*, *"14-Day Hair Care Challenge"*). Each day, the app unlocks a specific natural remedy task they must check off.
* **Tech:** Local database storing task states and unlock times.

### [ ] Feature 2: Daily Check-In & Streaks
* **Goal:** Build a daily usage habit.
* **Details:** A calendar view showing check-in progress. Checking in daily builds a streak (e.g., *"5 Days Streak!"*) and unlocks self-care badges (e.g. *"Glow Master"*, *"Hair Guru"*).
* **Tech:** SharedPreferences tracking daily login timestamps.

### [ ] Feature 3: Achievements & Self-Care Badges
* **Goal:** Give users a sense of progression and reward.
* **Details:** Display locked/unlocked badges based on user actions (e.g., read 10 skin tips, saved 5 favorites, shared 3 tips).
* **Tech:** Local counter variables in SharedPreferences.

---

## 🛠️ Category 2: Utility & Personalization (High App Value)

### [x] Feature 4: Saved Favorites System (Offline Bookmark)
* **Goal:** Let users bookmark their favorite beauty tips to access them offline anytime.
* **Details:** Add a Heart icon to the toolbar of the tip details page (`DetailsActivity`). Clicking the Heart toggles its state and saves/removes the tip ID in `SharedPreferences`. Add a "Favorites" tab or navigation option to view them.
* **Tech:** `SharedPreferences` (Offline XML key-value store).

### [ ] Feature 5: "My Beauty Diary" Progress Log (With Offline Photo Logs)
* **Goal:** High retention feature where users document their skin/hair improvements over time.
* **Details:** A private photo journal inside the app. Users write text notes and save photos of their face/hair to visually track progress of natural treatments.
* **Tech:** Android SQLite/Room database and local file storage.

### [ ] Feature 6: Daily Reminders & Habit Alarm Scheduler
* **Goal:** Integrate self-care habits into the user's daily calendar.
* **Details:** Custom alarm selectors for water intake, weekly face mask routines, or morning/night hair treatments. App triggers local notifications with motivating self-care tips.
* **Tech:** Android `AlarmManager` and `NotificationManager`.

### [ ] Feature 7: Interactive Beauty Calculator Tools
* **Goal:** High-interaction tool to calculate customized metrics.
* **Details:** Calculators for:
  1. *Water intake calculator:* Calculates daily water needs based on user weight and climate.
  2. *Skin Type Test:* A simple quiz that identifies dry/oily/sensitive skin.
* **Tech:** Simple offline logic and algorithms.

### [ ] Feature 8: Personal Beauty Profile Setup
* **Goal:** Personalize the app content to match the user's physical needs.
* **Details:** A short onboarding setup (Skin type, Hair texture, Age, and concerns). The main screen then highlights tips tagged specifically for their profile.
* **Tech:** Local content tag filter query.

### [x] Feature 9: Step-by-Step Timer Integration
* **Goal:** Help users time natural masks or massage routines safely.
* **Details:** If a tip text says *"leave for 15 minutes"*, show a *"Start 15-Min Timer"* button. Tapping it starts a countdown and alerts the user with an alarm when the mask is ready to wash off.
* **Tech:** Android `CountDownTimer` and `Vibrator`/Ringtone services.

### [ ] Feature 10: "My Remedy Notes"
* **Goal:** Let users customize natural recipes.
* **Details:** A text box under each tip where users add notes (e.g. *"I added 1 spoon of lemon juice to this face mask. Works great!"*).
* **Tech:** Room database mapping notes to Tip IDs.

---

## 📢 Category 3: Virality & Sharing (Organic Downloads)

### [ ] Feature 11: Visual Card Generator & Social Share
* **Goal:** Drive organic Play Store downloads through WhatsApp/Instagram statuses.
* **Details:** Instead of sharing plain text, the app renders the beauty tip on a beautiful image card with background graphics, watermarked with your app logo and Google Play Store link.
* **Tech:** Android `Canvas` View-to-Bitmap rendering and standard Share Intent.

### [x] Feature 12: "Shake to Suggest" Random Tip
* **Goal:** Add a fun, interactive easter egg that users share with friends.
* **Details:** When the user shakes their phone, the app plays a subtle sound, vibrates, and opens a random high-rated beauty tip.
* **Tech:** Android `SensorManager` (Accelerometer).

### [ ] Feature 13: Self-Care Tip of the Day Local Push Notification
* **Goal:** Bring users back to the app daily without server costs.
* **Details:** Built-in alarm triggers a daily push notification with a random beauty tip at a set time (e.g. 9:00 AM). Clicking it opens that tip directly.
* **Tech:** Android `WorkManager`.

### [ ] Feature 14: Custom User-Submitted Tips (Local Recipe Book)
* **Goal:** Empower users to write and save their own custom/family recipes.
* **Details:** Add a form where users type their own natural remedies. These custom tips are saved offline and listed under a *"My Custom Tips"* category.
* **Tech:** Room/SQLite Database.

---

## 📢 Category 4: Content & Usability Upgrades

### [x] Feature 15: Global Offline Search & Filter
* **Goal:** Help users find specific remedies immediately.
* **Details:** Add a search bar to categories. Users search keywords like *"acne"*, *"hair loss"*, or *"turmeric"* to find matching tips.
* **Tech:** Android `SearchView` filtering local adapters.

### [ ] Feature 16: Ingredient Directory & Glossary
* **Goal:** Increase content depth and usability.
* **Details:** A directory of ingredients (e.g., Honey, Aloe Vera, Lemon). Tapping an ingredient displays its benefits and a list of all beauty tips in the app that use it.
* **Tech:** Database relations mapping ingredients to tips.

### [ ] Feature 17: F.A.Q. & Myth Busters Section
* **Goal:** Educate users on natural care myths vs. facts (e.g., *"Toothpaste on pimples: Good or Bad?"*).
* **Details:** A readable myth-vs-fact Q&A section with clean toggle animations.
* **Tech:** Expandable RecyclerView layouts.

---

## 🎨 Category 5: UI/UX & Modern Enhancements

### [ ] Feature 18: Full Dark Theme Support
* **Goal:** Reduce eye strain for night-time readers.
* **Details:** Add a theme selector in Settings (Light, Dark, System Default).
* **Tech:** Android `AppCompatDelegate` night modes.

### [x] Feature 19: Text-to-Speech (Audio Tips Listener)
* **Goal:** Let users "listen" to the recipe steps while their hands are busy preparing or applying a mask.
* **Details:** A "Play/Pause" speaker button on the details screen that reads the instructions aloud.
* **Tech:** Android standard `TextToSpeech` API (100% free and native).

### [ ] Feature 20: Backup & Restore to Google Drive
* **Goal:** Protect user progress (favorites, progress photos, personal notes).
* **Details:** Let users back up their local database data to their personal Google Drive space and restore it if they switch devices.
* **Tech:** Google Drive REST API Client.

---

## 📚 Category 6: Content Expansion & Data Architecture

### [ ] Feature 21: Body & General Wellness Category (Content Expansion)
* **Goal:** Expand application scope to capture full-body wellness searches, bringing in new user demographics.
* **Details:** Add a new category for "Body Care & Wellness" covering subcategories like Stretch Marks, Neck Whitening, Body Scrub recipes, and Elbow/Knee Softening.
* **Tech:** Content templates and new data classes/models.

### [x] Feature 22: JSON-Based Local Content Database (Architecture Upgrade)
* **Goal:** Clean up the codebase and make adding/updating beauty tips 10x faster.
* **Details:** Currently, all beauty tips are hardcoded in static Kotlin files (like `Acne.kt`, `FairSkin.kt`). Migrate this static content into a single `tips_data.json` asset file. The app will parse this JSON dynamically to load categories, subcategories, and tips.
* **Tech:** Kotlin Serialization or Gson parsing local assets.

---

## 🧔 Category 7: Men's Grooming & Boys' Personal Care (Content Expansion)

### [ ] Feature 23: Men's Grooming & Beard Care Guide
* **Goal:** Expand user demographics by targeting boys/men looking for grooming solutions, significantly increasing Play Store search visibility.
* **Details:** A dedicated category for male-specific beauty needs:
  1. *Beard Growth & Patchy Beard remedies* (using natural oils and massage techniques).
  2. *Mustache Styling & Softening tips*.
  3. *Men's Hair Fall & Styling damage control*.
* **Tech:** Structured Kotlin object data classes or JSON integration.

### [ ] Feature 24: Razor Bumps & Shaving Care Companion
* **Goal:** Address a highly common skincare problem for boys.
* **Details:** Step-by-step remedies for curing razor burns, avoiding ingrown hairs, and making natural pre-shave/post-shave soothing masks.
* **Tech:** Adding list adapters matching existing ListModel layouts.

---

## 👩 Category 8: Advanced Skincare & Wellness Utilities

### [ ] Feature 25: Seasonal & Weather-Based Beauty Advisor
* **Goal:** Increase user engagement by suggesting relevant seasonal care lists.
* **Details:** Dynamic advice depending on current calendar month (e.g. *Summer Glow checklist* with hydration remedies, *Winter Care checklist* with dry skin/chapped lips remedies, *Monsoon Hair Fall control*).
* **Tech:** Standard local system Calendar class checking local date.

### [ ] Feature 26: Daily Water Intake & Hydration Tracker (Glowing Skin Habit)
* **Goal:** Build strong daily user retention (hydration is directly tied to skin glow).
* **Details:** A simple, beautiful tracking card on the main screen where users tap to check off water glasses drank. App triggers gentle notifications: *"Drink a cup of water now for naturally glowing skin!"*
* **Tech:** SharedPreferences storage and basic alarms.

### [ ] Feature 27: Women's Skincare Cycle Advisor (Hormonal Acne Tracker)
* **Goal:** Highly personalized tool targeting female skin wellness.
* **Details:** Let female users optionally input cycle details to predict hormonal spikes. Suggest preventative remedies (e.g. clay masks or tea tree routines) exactly when hormonal acne is most likely to breakout.
* **Tech:** Offline cycle-tracking calculations stored in SharedPreferences.

### [ ] Feature 28: Cosmetic Ingredient Safety Analyzer (Offline Dictionary)
* **Goal:** Elevate app utility into a pocket advisor.
* **Details:** An offline dictionary lookup for common product ingredients (e.g., Parabens, Retinol, Hyaluronic Acid, Sulfates). Indicates safety rating (safe, avoid, hazard) and skin type compatibility.
* **Tech:** Local SQLite/Room DB asset matching chemical terms.
