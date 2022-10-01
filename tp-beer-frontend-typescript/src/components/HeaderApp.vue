<template>
  <v-toolbar color="primary lighten-1" padless>
    <v-row justify="center" no-gutters>
      <v-col class="primary lighten-2 py-4 text-center white--text" cols="12">
        <router-link to="/">
          <v-btn color="white" text rounded class="my-2">HOME</v-btn>
        </router-link>
        |
        <router-link to="/formbeer">
          <v-btn color="white" text rounded class="my-2">SAVE BEER</v-btn>
        </router-link>
      </v-col>
    </v-row>
  </v-toolbar>
</template>

<script>
export default {
  name: "HeaderApp"
}
</script>