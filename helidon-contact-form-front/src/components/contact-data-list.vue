<template>
  <ul id="example-2">
    <li v-for="(item, index) in items" :key="index">
        <div>{{}}</div>
      <div>{{ item.name }}</div>
      <div>{{ item.email }}</div>
      <div>{{ item.content }}</div>
    </li>
  </ul>
</template>

<script>
import axios from "axios";
export default {
  data() {
    return {
      items: [
        {},
      ],
    };
  },
  mounted() {
    axios
      .get("/api/get_all")
      .then((response) => {
        console.log(response.data);
        this.items = response.data;
      })
      .catch((err) => {
        console.log(err);
      });
  },
};
</script>
