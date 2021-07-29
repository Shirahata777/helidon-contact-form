<template>
  <div class="relative">
    <form class="grid grid-cols-1 gap-6 m-16" @submit.prevent="saveContactData">
      <label class="block">
        <span class="text-gray-700">Full name</span>
        <input
          type="text"
          class="
            mt-1
            block
            w-full
            rounded-md
            border-gray-300
            shadow-sm
            focus:border-indigo-300
            focus:ring focus:ring-indigo-200 focus:ring-opacity-50
          "
          placeholder=""
          id="name"
          v-model="name"
        />
      </label>
      <label class="block">
        <span class="text-gray-700">Email address</span>
        <input
          type="email"
          class="
            mt-1
            block
            w-full
            rounded-md
            border-gray-300
            shadow-sm
            focus:border-indigo-300
            focus:ring focus:ring-indigo-200 focus:ring-opacity-50
          "
          placeholder="john@example.com"
          id="email"
          v-model="email"
        />
      </label>
      <label class="block">
        <span class="text-gray-700">Content of inquiry</span>
        <textarea
          class="
            mt-1
            block
            w-full
            rounded-md
            border-gray-300
            shadow-sm
            focus:border-indigo-300
            focus:ring focus:ring-indigo-200 focus:ring-opacity-50
          "
          rows="3"
          id="content"
          v-model="content"
        ></textarea>
      </label>
      <div class="block">
        <div class="mt-2">
          <div>
            <label class="inline-flex items-center">
              <input
                type="submit"
                value="Submit"
                class="form-input rounded-full"
              />
            </label>
          </div>
        </div>
      </div>
    </form>
  </div>
</template>
<script>
import axios from "axios";
export default {
  data() {
    return {
      name: "",
      email: "",
      content: "",
      msg: "",
    };
  },
  methods: {
    saveContactData: function () {
      axios
        .get("/api/save", {
          params: {
            name: this.name,
            email: this.email,
            content: this.content,
          },
        })
        .then(response => {
          console.log(response.data)
        })
        .catch((err) => {
          console.log(err)
        })
      }
    
  },
};
</script>
<style lang="scss">
</style>