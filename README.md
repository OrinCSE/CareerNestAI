# CareerNestAI

An intelligent, AI-driven native Android ecosystem designed to revolutionize how job seekers optimize their resumes and discover high-affinity career opportunities. Built using Java, XML, and robust document-parsing patterns, CareerNestAI brings desktop-grade ATS optimization right into your pocket.

---

## Key Features

* **🧠 AI Resume Analyzer:** Seamlessly upload CVs in PDF, DOC, or DOCX formats directly via the mobile dashboard. The engine extracts text and prints objective evaluation metrics onto the screen.
* **📊 Smart Compatibility Engine:** Calculates real-time affinity and transparently displays alignment scores (e.g., "85% Match") directly on specific vacancy listings.
* **🎛️ Multi-Tier Filter Subsystem:** An interactive, bug-free Bottom Sheet component allowing candidates to refine active query feeds instantly by job settings, compensation, and difficulty configurations.
* **📈 Executive Applicant Dashboard:** A modern, visual command center presenting real-time summaries of an applicant’s active pipeline, tracking Applied vacancies, active Interviews, and potential Offers.

---

## 🛠️ Architecture & Tech Stack

* **Frontend Framework:** Native Android UI utilizing optimized XML layouts, material design configurations, and flexible `RelativeLayout` and `LinearLayout` architectures.
* **Core Programming Paradigm:** Object-Oriented Java development with explicit Model-View-Controller (MVC) decoupling.
* **State Management:** Modern `ActivityResultLauncher` implementations ensuring clean, low-overhead media and filesystem ingestion.
* **Asynchronous Lifecycles:** Structured adapter handling to avoid memory leaks during heavy list rendering or vector computations.

---

## 📸 Core Interface Previews

### 1. Unified Applicant Dashboard & Smart Document Picker
The dynamic central panel displays active recruitment progress analytics, coupled with a prominent container triggering full storage access loops for instant CV verification.

| Dashboard Hub | Active Document Uploader |
| :---: | :---: |
| <img src="https://github.com/OrinCSE/CareerNestAI/blob/66bf8379b8baf6f490e6c5e78a348032a67a93bd/Screenshot%202026-04-22%20195952.png" width="280" alt="Dashboard Hub"/> | <img src="https://raw.githubusercontent.com/placeholder-images/uploader.png" width="280" alt="Active Document Uploader"/> |

### 2. Tailored Vacancy Discovery & Advanced Filtering Subsystem
The multi-tier matching grid presents continuous opportunity card listings integrated directly with localized constraints to separate complex entry levels on the fly.

| Tailored Matching Grid | Advanced Filter System |
| :---: | :---: |
| <img src="https://raw.githubusercontent.com/placeholder-images/feed.png" width="280" alt="Tailored Matching Grid"/> | <img src="https://raw.githubusercontent.com/placeholder-images/filters.png" width="280" alt="Advanced Filter System"/> |

---

## ⚙️ Project Lifecycle Flow 
              +---------------------------------------+
              |  Problem Identification & Extraction  |
              +---------------------------------------+
                                  |
                                  v
              +---------------------------------------+
              |    System Review & Data Ingestion     |
              +---------------------------------------+
                                  |
                                  v
              +---------------------------------------+
              |  Identification of Key Core Skills   |
              +---------------------------------------+
                                  |
                                  v
              +---------------------------------------+
              |       Candidate CV Processing         |
              +---------------------------------------+
---

## 📦 Requirements & Installation

Before running the application, ensure your engineering development environment conforms to the parameters below:

* **Android Studio:** Jellyfish | 2023.3.1 or higher
* **Minimum SDK Platform:** Android 10.0 (API Level 29)
* **Target SDK Version:** API Level 34 (Android 14)
* **Hardware Profile:** Compiled and verified on modern consumer setups (e.g., Intel Core configurations with sufficient virtual memory capabilities).

### Deployment Sprints

1. **Clone the Repository:**
   ```bash
   git clone [https://github.com/yourusername/CareerNestAI.git](https://github.com/yourusername/CareerNestAI.git)
