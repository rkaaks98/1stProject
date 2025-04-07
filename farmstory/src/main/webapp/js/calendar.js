let today = new Date(); // 현재 날짜
let currentYear = today.getFullYear();
let currentMonth = today.getMonth(); // 0(1월) ~ 11(12월)

const monthNames = [
  "January",
  "February",
  "March",
  "April",
  "May",
  "June",
  "July",
  "August",
  "September",
  "October",
  "November",
  "December",
];
//공휴일 목록 (YYYY-MM-DD 형식)
const holidays = {
  "2025-01-01": "신정",
  "2025-01-27": "임시공휴일",
  "2025-01-28": "설연휴",
  "2025-01-29": "설날",
  "2025-01-30": "설연휴",
  "2025-03-01": "삼일절",
  "2025-05-05": "어린이날",
  "2025-06-06": "현충일",
  "2025-08-15": "광복절",
  "2025-10-03": "개천절",
  "2025-10-09": "한글날",
  "2025-12-25": "크리스마스",
};

function generateCalendar(year, month) {
  let calendar = document.getElementById("calendar");
  let monthYear = document.getElementById("monthYear");

  let firstDay = new Date(year, month, 1).getDay();
  let lastDate = new Date(year, month + 1, 0).getDate();

  // "Month YYYY" 형식으로 표시
  monthYear.innerText = `${monthNames[month]} ${year}`;

  // 기존 달력 초기화
  while (calendar.rows.length > 1) {
    calendar.deleteRow(1);
  }

  let row = calendar.insertRow();
  let cellIndex = 0;

  for (let i = 0; i < firstDay; i++) {
    row.insertCell();
    cellIndex++;
  }

  for (let day = 1; day <= lastDate; day++) {
    let cell = row.insertCell();
    cell.innerText = day;

    // 오늘 날짜 강조 (기존 "today" 클래스 삭제 후 추가)
    if (
      year === new Date().getFullYear() &&
      month === new Date().getMonth() &&
      day === new Date().getDate()
    ) {
      cell.classList.add("today");
    }

    //공휴일 체크 & 표시
    let dateKey = `${year}-${(month + 1).toString().padStart(2, "0")}-${day
      .toString()
      .padStart(2, "0")}`;
    if (holidays[dateKey]) {
      cell.classList.add("holiday");

      let holidayBox = document.createElement("div");
      holidayBox.classList.add("holiday-box");
      holidayBox.innerText = "휴일";
      cell.appendChild(holidayBox);
    }

    cellIndex++;
    if (cellIndex % 7 === 0) {
      row = calendar.insertRow();
    }
  }
}

function prevMonth() {
  currentMonth--;
  if (currentMonth < 0) {
    currentMonth = 11;
    currentYear--;
  }
  generateCalendar(currentYear, currentMonth);
}

function nextMonth() {
  currentMonth++;
  if (currentMonth > 11) {
    currentMonth = 0;
    currentYear++;
  }
  generateCalendar(currentYear, currentMonth);
}

function goToToday() {
  let now = new Date(); // 오늘 날짜를 다시 가져옴
  currentYear = now.getFullYear();
  currentMonth = now.getMonth();
  generateCalendar(currentYear, currentMonth);
}

generateCalendar(currentYear, currentMonth);
