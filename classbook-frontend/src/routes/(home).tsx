import { Button, Grid, Stack, TextField } from "@suid/material";

export default function Home() {
  return (
    <div class="w-full mt-8 grid place-items-center gap-3">
      <h1 class="text-4xl">Classbook</h1>
      <Stack class="w-[380px] p-3 border-gray-200 rounded border-2" spacing={1}>
        <TextField required label="Username" />
        <TextField required label="Password" />
        <Button variant="contained">Log In</Button>
        <Button variant="outlined">Register</Button>
      </Stack>
    </div>
  );
}
