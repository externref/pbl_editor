import { sveltekit } from '@sveltejs/kit/vite';
import { defineConfig } from 'vite';

export default defineConfig({
	optimizeDeps: {
		exclude: [ "svelte-codemirror-editor","clsx", "@codemirror/view", "@codemirror/state", "@codemirror/commands", "@codemirror/language", "codemirror", "@codemirror/lang-javascript", "@codemirror/lang-java", "@codemirror/lang-rust", "@codemirror/lang-python", "@codemirror/theme-one-dark", "@codemirror/lang-markdown", "thememirror",  /* ... */],
	},
	plugins: [sveltekit()]
});
