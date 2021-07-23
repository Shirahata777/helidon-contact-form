<template>
  <div class="relative">
    <form class="grid grid-cols-1 gap-6 m-16">
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
export default {};
</script>
<style lang="scss">
</style>