# BeautyTips - Ultimate Growth & Engagement Features Backlog

This document tracks all zero-budget features designed to maximize user retention, virality, and organic downloads. Completed features are checked off below.

---

## 🎮 Category 1: Gamification & Engagement (Habit Building)

### [x] Feature 1: 7-Day / 14-Day Beauty Challenges
* **Goal:** Increase daily active usage (DAUs) by setting up structured, multi-day self-care routines.
* **Details:** Users join challenges (e.g. *"7-Day Glowing Skin Challenge"*, *"14-Day Hair Care Challenge"*). Each day, the app unlocks a specific natural remedy task they must check off.
* **Tech:** Local database storing task states and unlock times.

### [x] Feature 2: Daily Check-In & Streaks
* **Goal:** Build a daily usage habit.
* **Details:** A calendar view showing check-in progress. Checking in daily builds a streak (e.g., *"5 Days Streak!"*) and unlocks self-care badges (e.g. *"Glow Master"*, *"Hair Guru"*).
* **Tech:** SharedPreferences tracking daily login timestamps.

### [x] Feature 3: Achievements & Self-Care Badges
* **Goal:** Give users a sense of progression and reward.
* **Details:** Display locked/unlocked badges based on user actions (e.g., read 10 skin tips, saved 5 favorites, shared 3 tips).
* **Tech:** Local counter variables in SharedPreferences.

---

## 🛠️ Category 2: Utility & Personalization (High App Value)

### [x] Feature 4: Saved Favorites System (Offline Bookmark)
* **Goal:** Let users bookmark their favorite beauty tips to access them offline anytime.
* **Details:** Add a Heart icon to the toolbar of the tip details page (`DetailsActivity`). Clicking the Heart toggles its state and saves/removes the tip ID in `SharedPreferences`. Add a "Favorites" tab or navigation option to view them.
* **Tech:** `SharedPreferences` (Offline XML key-value store).

### [x] Feature 5: "My Beauty Diary" Progress Log (With Offline Photo Logs)
* **Goal:** High retention feature where users document their skin/hair improvements over time.
* **Details:** A private photo journal inside the app. Users write text notes and save photos of their face/hair to visually track progress of natural treatments.
* **Tech:** Android SQLite/Room database and local file storage.

### [x] Feature 6: Daily Reminders & Habit Alarm Scheduler
* **Goal:** Integrate self-care habits into the user's daily calendar.
* **Details:** Custom alarm selectors for water intake, weekly face mask routines, or morning/night hair treatments. App triggers local notifications with motivating self-care tips.
* **Tech:** Android `AlarmManager` and `NotificationManager`.

### [x] Feature 7: Interactive Beauty Calculator Tools
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

### [x] Feature 11: Visual Card Generator & Social Share
* **Goal:** Drive organic Play Store downloads through WhatsApp/Instagram statuses.
* **Details:** Instead of sharing plain text, the app renders the beauty tip on a beautiful image card with background graphics, watermarked with your app logo and Google Play Store link.
* **Tech:** Android `Canvas` View-to-Bitmap rendering and standard Share Intent.

### [x] Feature 12: "Shake to Suggest" Random Tip
* **Goal:** Add a fun, interactive easter egg that users share with friends.
* **Details:** When the user shakes their phone, the app plays a subtle sound, vibrates, and opens a random high-rated beauty tip.
* **Tech:** Android `SensorManager` (Accelerometer).

### [x] Feature 13: Self-Care Tip of the Day Local Push Notification
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

### [x] Feature 26: Daily Water Intake & Hydration Tracker (Glowing Skin Habit)
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

---

## ⚙️ Category 9: App Updates & Maintenance (App Life-Cycle)

### [x] Feature 29: Force Update Functionality
* **Goal:** Ensure all active users are on the latest, bug-free, and Play-Store-compliant version of the app.
* **Details:** The app checks the minimum required version configuration (using Firebase Remote Config, a simple hosted JSON API, or standard Google Play In-App Updates) on startup. If the current version is outdated, displays a non-dismissible dialog directing users to Google Play to update the app.
* **Tech:** Google Play In-App Updates API or Firebase Remote Config + Play Store Intent redirection.

### [x] Feature 30: Home Screen Layout Optimization (Quick-Action Grid)
* **Goal:** Move the main Categories list above the fold to prioritize core app functionality.
* **Details:** Replaced the three large, full-width vertical stacked banners (Challenges, Check-In, Water Tracker) with a compact, 3-column horizontal grid. Subtitle texts are dynamically shortened (e.g., check-in streak, water intake fraction) to fit perfectly side-by-side.
* **Tech:** Responsive LinearLayout weight distribution and localized dynamic text formatting.

---

## 🌍 Localization & Translation Coding Guidelines

To support multiple languages and prevent packaging build errors:
1. **No Hardcoded/Static Strings:** Under no circumstances should raw string literals be hardcoded in layout XML files or Kotlin source files. All user-facing strings must be defined inside `strings.xml` files.
2. **Mandatory Translations:** Any new string resource added to the base `strings.xml` must also be translated and added to the other 8 localized resource directories: German (`values-de`), Spanish (`values-es`), French (`values-fr`), Hindi (`values-hi`), Japanese (`values-ja`), Korean (`values-ko`), Portuguese (`values-pt`), and Russian (`values-ru`).
3. **Apostrophe Escaping:** In XML string resources, all apostrophes (`'`) must be properly escaped as `\'` or enclosed in double quotes (e.g. `Complete Today\'s Routine` or `"Complete Today's Routine"`).
4. **Formatting Constraints:** All string resources containing dynamic format parameters (like `%1$d` or `%1$s`) must be defined with the `formatted="false"` attribute to ensure AAPT2 compiler compatibility.


---

## 🧴 Category 10: Personalization & Smart Tools

### [ ] Feature 31: Beauty Routine Builder
* **Goal:** Let users create their own fully custom morning and night beauty routines — a unique offline differentiator.
* **Details:** Users add steps in sequence (e.g. *"Step 1: Cleanse → Step 2: Toner → Step 3: Moisturize"*). Each step has an optional built-in countdown timer. Save multiple named routines (e.g. *"Morning Glow Routine"*, *"Sunday Deep Care"*) and launch them with one tap.
* **Tech:** Room database storing routine steps; `CountDownTimer` per step; RecyclerView with drag-to-reorder.

### [ ] Feature 32: Mood-Based Tip Picker
* **Goal:** Drive deeper engagement with existing content by matching tips to how the user\'s skin feels right now.
* **Details:** A card asks *"How is your skin feeling today?"* with options: Dull, Oily, Dry, Tired, Sensitive, Glowing. The app instantly shows curated tips matching that mood/condition.
* **Tech:** Tag-based offline filter on existing JSON tips data; no extra database required.

### [ ] Feature 33: Tip Rating System
* **Goal:** Surface the most effective tips to the top over time, giving users a sense of ownership.
* **Details:** A 👍 / 👎 button on each tip detail screen. Ratings stored locally per Tip ID. A *"Top Rated"* section shows the 10 most liked tips across all categories.
* **Tech:** `SharedPreferences` storing like/dislike counts per Tip ID; sorted RecyclerView adapter.

### [ ] Feature 34: Face Shape Guide
* **Goal:** Provide hyper-personalized makeup and hairstyle tips based on the user\'s face shape.
* **Details:** A short interactive quiz (4–5 questions about facial proportions) determines face shape (Oval, Round, Square, Heart, Diamond). Results show personalized contouring tips and ideal hairstyle recommendations.
* **Tech:** Offline quiz logic similar to existing Skin Type Test; result screen with image illustrations.

### [ ] Feature 35: Hair Porosity Test
* **Goal:** Help users understand their hair type for better remedy and product decisions.
* **Details:** A 3-step interactive quiz tests hair porosity (Low / Normal / High). Result explains what it means and links to targeted remedies (e.g. protein treatments for high porosity, lightweight oils for low porosity).
* **Tech:** Simple quiz scoring logic; result links to existing hair tips in the app.

### [ ] Feature 36: Age Group Filter
* **Goal:** Make tips feel personally relevant by surfacing age-appropriate content.
* **Details:** Users set their age group once in Settings (Teens / 20s / 30s / 40s+). Tips relevant to their age show a *"Best for your age"* badge. A *"For You"* section shows only age-matched tips.
* **Tech:** Age tag field added to JSON tips data; `SharedPreferences` storing selected age group; filtered adapter.

### [ ] Feature 37: Allergy & Ingredient Avoid List
* **Goal:** Protect users from harmful suggestions by flagging ingredients they are allergic or sensitive to.
* **Details:** Users add ingredients to avoid (e.g. Lemon, Honey, Coconut Oil) in Settings. Any tip mentioning those ingredients shows a ⚠️ *"Contains allergen you marked"* warning banner on its card.
* **Tech:** `SharedPreferences` storing a Set of avoided ingredient keywords; string search in tip text on adapter bind.

### [x] Feature 38: Beauty Wellness Score
* **Goal:** Gamify the overall self-care habit by combining multiple health signals into one motivating score.
* **Details:** A *"Beauty Wellness Score"* (0–100) shown as a circular progress ring on the home screen. Calculated from: daily water intake progress + check-in streak + active challenge progress + tips read today.
* **Tech:** Read existing SharedPrefs for water count, streak, challenge data; formula computed in `onResume`; no new database needed.

---

## 📅 Category 11: Engagement & Retention

### [ ] Feature 39: Beauty Calendar & Routine Planner
* **Goal:** Help users plan and stick to their weekly self-care schedule with a visual calendar.
* **Details:** A monthly calendar view where users schedule recurring routines (e.g. *"Face Mask every Sunday"*, *"Hair Oil every Wednesday"*). Scheduled days show colored dots. Tapping a day shows the planned routine with a check-off button.
* **Tech:** Room database storing scheduled events per day-of-week; simple 7-column week row UI.

### [ ] Feature 40: Daily Mini Challenge
* **Goal:** Deliver one fresh, quick, achievable beauty challenge every day to build a micro-habit.
* **Details:** Each day a new *"5-Minute Challenge"* appears (e.g. *"Massage your scalp for 5 minutes"*, *"Apply a cold spoon under eyes for 2 minutes"*). Users tap *"Done"* to complete it and build a mini-challenge streak. Different from the 7/14-day challenges — these are single-step daily tasks.
* **Tech:** Deterministic daily index from `Calendar.DAY_OF_YEAR` into a local challenges array; streak in `SharedPreferences`.

### [ ] Feature 41: Tip of the Week (Featured Content)
* **Goal:** Give returning users a reason to open the app every week with highlighted premium content.
* **Details:** One tip is featured at the top of the home screen in a highlighted banner card. Auto-rotates weekly using a deterministic weekly index. Shows a *"This Week\'s Pick"* badge with the tip title and category.
* **Tech:** `Calendar.WEEK_OF_YEAR` as index into tip list; no backend required; home screen card view.

### [ ] Feature 42: Recently Viewed History
* **Goal:** Let users quickly return to tips they were reading without searching again.
* **Details:** Every time a user opens a tip detail screen, its ID and title are stored in a *"Recently Viewed"* list (max 15 entries, newest first). Accessible from the Search screen or Settings menu.
* **Tech:** `SharedPreferences` storing a JSON array of last 15 tip IDs/titles; displayed in a simple RecyclerView.

---

## 🎓 Category 12: Education & Learning

### [ ] Feature 43: Beauty Myths Quiz
* **Goal:** Drive weekly re-engagement through a fun, shareable education format — different from the static FAQ (Feature 17) which is expandable Q&A content.
* **Details:** A weekly *"True or False"* quiz with 5 beauty myths (e.g. *"Lemon permanently removes dark spots — True or False?"*). Users answer all 5 and see a score with correct explanations. Results are shareable as a card image.
* **Tech:** Static quiz data in a Kotlin object; `SharedPreferences` storing last played week to prevent repeats; score result screen with share option.

### [ ] Feature 44: Ingredient Spotlight (Daily Feature)
* **Goal:** Educate users on natural ingredients — complementary to the Ingredient Directory (Feature 16) which is a full glossary; this is a curated daily highlight card.
* **Details:** One natural ingredient spotlighted daily on the home screen (e.g. *"Today: Turmeric — Benefits, Uses & 3 Recipes"*). Tapping opens a dedicated page with skin/hair benefits and links to related tips already in the app.
* **Tech:** Deterministic daily index from `Calendar.DAY_OF_YEAR` into a local ingredients array; no backend.

### [ ] Feature 45: Beginner\'s Guide Mode
* **Goal:** Reduce new-user churn by giving first-time users a clear, friendly starting path.
* **Details:** On first launch, users are offered a *"Start Here"* 7-day beginner program — one simple tip or habit per day, unlocking sequentially. Day 1: *"Cleanse twice a day"*, Day 2: *"Always moisturize after washing"*, etc. A progress bar shows progress through the guide.
* **Tech:** `SharedPreferences` tracking current day index; static 7-item beginner tips array; home screen banner card.

