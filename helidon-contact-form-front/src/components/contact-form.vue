<template>
  <div>
    <form action="" class="flex flex-col">
      <input
        type="text"
        name="name"
        id="name"
        placeholder="お名前を入力してください！"
        class="form-input px-4 py-3 rounded-full"
      />
      <input
        type="email"
        name="email"
        id="email"
        placeholder="メールアドレスを入力してね！"
        class="form-input px-4 py-3 rounded-full"
      />
      <input
        type="text"
        name="contact"
        id="contact"
        placeholder="お問い合わせ内容を入力してください！"
        class="form-input px-4 py-3 rounded-full"
      />
      <input type="submit" value="送信" class="form-input px-4 py-3 rounded-full" />
    </form>
  </div>
</template>
<script>
export default {};
</script>
<style lang="scss">
</style>