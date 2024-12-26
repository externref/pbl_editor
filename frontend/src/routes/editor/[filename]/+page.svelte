<script lang="ts">
	import CodeMirror from 'svelte-codemirror-editor';
	import { onMount } from 'svelte';
	import { page } from '$app/stores';
	import { get } from 'svelte/store';
	import * as themes from 'thememirror';
	import { javascript } from '@codemirror/lang-javascript';
	import { python } from '@codemirror/lang-python';
	import { html } from '@codemirror/lang-html';
	import { css } from '@codemirror/lang-css';
	import { json } from '@codemirror/lang-json';
	import { markdown } from '@codemirror/lang-markdown';
	import { java } from '@codemirror/lang-java';
	import { cpp } from '@codemirror/lang-cpp';
	import { rust } from '@codemirror/lang-rust';
	import { autocompletion } from '@codemirror/autocomplete';
	import { HighlightStyle, syntaxHighlighting } from '@codemirror/language';
	import { defaultHighlightStyle } from '@codemirror/highlight';

	let value: string = '';
	let file: string = 'default.txt';
	let selectedTheme = themes.dracula;
	let extensions: any;

	const extensionToLanguageConfig = {
  js: javascript,
  ts: () => {
    return javascript();
  },
  html:html, 
  json: json,
  css: css,
  cpp: cpp,
  rs: rust,
  jsx: javascript,
  tsx: javascript,
  py: python,
  java: java,
  md: markdown
};
	// Function to handle saving the file
	async function handleKeyDown(event: KeyboardEvent) {
		if (event.ctrlKey && event.key === 's') {
			event.preventDefault(); 
			try {
				const response = await fetch('http://localhost:8080/write', {
					method: 'POST',
					headers: {
						'Content-Type': 'application/json'
					},
					body: JSON.stringify({
						path: file, 
						code: value 
					})
				});

				if (response.ok) {
					alert('File saved successfully!');
				} else {
					alert('Error saving file: ' + response.statusText);
				}
			} catch (error) {
				console.error('Error:', error);
				alert('Failed to save file. Please try again.');
			}
		}
	}

	// Function to load the file content
	async function loadFile(filename: string) {
		try {
			const response = await fetch('http://localhost:8080/read', {
				method: 'POST',
				headers: {
					'Content-Type': 'application/json',
				},
				body: JSON.stringify({ path: filename }),
			});
			if (response.ok) {
				const fileContent = await response.text();
				value = JSON.parse(fileContent);
			} else {
				alert('Error loading file: ' + response.statusText);
			}
		} catch (error) {
			console.error('Error loading file:', error);
			alert('Failed to load file.');
		}
	}

	// Function to determine language extensions based on the file type
	function determineExtensions(filename: string) {
		const ext = filename.split('.').pop();
		return ext;
	}

	// onMount to load the file and initialize language extension based on file
	onMount(() => {
		const filename = get(page).params.filename;
		if (filename) {
			file = filename;
			loadFile(file);
		}
		window.addEventListener('keydown', handleKeyDown);
		return () => {
			window.removeEventListener('keydown', handleKeyDown);
		};
	});

	// Adding highlighting style
	
</script>

<div class="header flex justify-between items-center border-b px-4">
	<span class="filename text-lg font-bold">{file}</span>
	<select bind:value={selectedTheme} class="theme-select border">
		{#each Object.keys(themes) as themeKey}
			<option value={themes[themeKey]}>{themeKey}</option>
		{/each}
	</select>
</div>



<CodeMirror
	bind:value
	lang={(extensionToLanguageConfig[file.split(".")[file.split(".").length-1] as keyof typeof extensionToLanguageConfig  ]|| extensionToLanguageConfig.md)() }  
	theme={selectedTheme}
	styles={{
		'&': {
			width: '100%',
			height: '95vh',
		},
	}}
/>
