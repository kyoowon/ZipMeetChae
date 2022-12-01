<template>
  <div class="text-center section">
    <p class="text-lg font-medium text-gray-600 mb-6">
      비대면으로 예약된 일정을 확인하세요
    </p>
    <v-calendar
      class="custom-calendar max-w-full max-h-full"
      :masks="masks"
      :attributes="attributes"
      disable-page-swipe
      is-expanded
    >
      <template v-slot:day-content="{ day, attributes }">
        <div class="flex flex-col h-full z-10 overflow-hidden">
          <span class="day-label text-sm text-gray-900">{{ day.day }}</span>
          <div class="flex-grow overflow-y-auto overflow-x-auto">
            <p
              v-for="attr in attributes"
              :key="attr.key"
              class="text-xs leading-tight rounded-sm p-1 mt-0 mb-1"
              :class="attr.customData.class"
            >
              {{ attr.customData.title }}
            </p>
          </div>
        </div>
      </template>
    </v-calendar>
    <pre id="content" style="white-space: pre-wrap">{{ value }}</pre>
  </div>
</template>

<script>
import moment from "moment";
import { mapActions } from "vuex";
// import v-calendar from "v";
let memberStore = "memberStore";

export default {
  async created() {
    let dates = await this.listCalenderEvents();
    console.log(dates);
    if (dates){
      dates.forEach((date, index)=>{
        if (date.str.hangoutLink){
          this.attributes.push({
          key : index,
          customData: {
            title: `${date.str.summary}
                    ${date.str.organizer.email}
                    ${date.str.hangoutLink}
                    ${date.str.start.date || date.str.start.dateTime}
            `,
            class: "bg-red-600",
          },
          dates:   moment(date.str.start.date || date.str.start.dateTime).format("YYYY-MM-DD:HH:mm:ss"),
        })
        }
      })
    }
    console.log(this.attributes);
  },
  methods: {
    ...mapActions(memberStore, ["listCalenderEvents"]),
  },
  data() {
    return {
      value : "",
      masks: {
        weekdays: "WWW",
      },
      attributes: [],
    };
  },
};
</script>

<style lang="postcss" scoped>
::-webkit-scrollbar {
  width: 0px;
}
::-webkit-scrollbar-track {
  display: none;
}
/deep/ .custom-calendar.vc-container {
  --day-border: 1px solid black;
  --day-border-highlight: 1px solid black;
  --day-width: 90px;
  --day-height: 90px;
  --weekday-bg: black;
  --weekday-border: 1px solid black;
  border-radius: 0;
  width: 100%;
  & .vc-header {
    background-color: #f1f5f8;
    padding: 10px 0;
  }
  & .vc-weeks {
    padding: 0;
  }
  & .vc-weekday {
    background-color: var(--weekday-bg);
    border-bottom: var(--weekday-border);
    border-top: var(--weekday-border);
    padding: 5px 0;
  }
  & .vc-day {
    padding: 0 5px 3px 5px;
    text-align: left;
    height: var(--day-height);
    min-width: var(--day-width);
    background-color: black;
    &.weekday-1,
    &.weekday-7 {
      background-color: black;
    }
    &:not(.on-bottom) {
      border-bottom: var(--day-border);
      &.weekday-1 {
        border-bottom: var(--day-border-highlight);
      }
    }
    &:not(.on-right) {
      border-right: var(--day-border);
    }
  }
  & .vc-day-dots {
    margin-bottom: 5px;
  }
}
</style>
