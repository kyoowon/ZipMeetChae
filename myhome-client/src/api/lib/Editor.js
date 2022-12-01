import Vue from "vue";
import Editor from "@tinymce/tinymce-vue";

Vue.use(Editor, {
    // componentPrefix: 'e',  // Use <vc-calendar /> instead of <v-calendar />
  });
  

  Vue.component("v-editor", Editor);
