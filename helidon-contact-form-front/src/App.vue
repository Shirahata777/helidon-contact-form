<template>
  <div>
    <ContactForm/>
    <ContactDataList />
  </div>
</template>

<script>
import ContactForm from './components/contact-form.vue'
import ContactDataList from './components/contact-data-list.vue'

export default {
  name: 'App',
  components: {
    ContactForm,
    ContactDataList
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
