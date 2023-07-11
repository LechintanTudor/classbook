import solid from "solid-start/vite";
import { defineConfig } from "vite";
import suid from "@suid/vite-plugin";

export default defineConfig({
  plugins: [solid({ ssr: false }), suid()],
});
