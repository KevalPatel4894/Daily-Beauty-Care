---
name: daily_beauty_care_app
description: Comprehensive coding guidelines, database schemas, localization standards, and architecture for the Daily Beauty Care Android project. Use this skill whenever working on Daily Beauty Care features, bug fixes, or maintenance.
---

# Daily Beauty Care Android Project Architecture & Guidelines

## 1. Project Stack & Architecture
- **Language**: 100% Kotlin with AndroidX & Material Components.
- **Min SDK / Target SDK**: 24 / 34 (Supported compileSdk up to 36).
- **Architecture**: Activity-based navigation with helper data classes and SQLite open helpers.
- **AdMob Integration**: Managed via `AdManager.showBannerAd(activity, adContainer)`.

## 2. Layout & RelativeLayout Rules (CRITICAL)
- **Bottom Banner (`ad_view`) Anchor Constraints**:
  - `ad_view` is placed at `layout_alignParentBottom="true"`.
  - Content containers (e.g. `FrameLayout`, `RecyclerView`, `NestedScrollView`) anchored via `layout_above="@+id/ad_view"` AND `layout_below="@+id/toolBar"` MUST **NOT** HAVE `layout_alignParentBottom="true"`.
  - Floating Action Buttons (FABs) anchored via `layout_above="@+id/ad_view"` MUST HAVE `layout_alignParentBottom="true"` as a fallback anchor when `ad_view` becomes `GONE`.

## 3. Localization & Translation Guidelines
- **Zero Hardcoded Strings**: All user-facing strings must be in `strings.xml`.
- **Mandatory 8 Localizations**: `values-de`, `values-es`, `values-fr`, `values-hi`, `values-ja`, `values-ko`, `values-pt`, `values-ru`.
- **Apostrophes**: Must be escaped as `\'`.
- **Format Parameters**: Strings with `%1$d`, `%1$s`, etc., must be declared with `formatted="false"`.

## 4. SQLite Data Persistence
- Database helpers inherit from `SQLiteOpenHelper`:
  - `CustomTipDbHelper` (`custom_tips.db`)
  - `DiaryDbHelper` (`diary.db`)
  - `RemedyNotesDbHelper` (`remedy_notes.db`)
  - `RoutineDbHelper` (`routine_builder.db`)
