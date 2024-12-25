<script lang="ts">
	import CodeMirror from 'svelte-codemirror-editor';
	import { onMount } from 'svelte';
	import * as themes from 'thememirror';

	let value: string = ''; // Content of the editor
	let file: string = 'default.txt'; // Default filename
	let selectedTheme = themes.dracula;
   

	// Function to handle Ctrl+S
	async function handleKeyDown(event: KeyboardEvent) {
		if (event.ctrlKey && event.key === 's') {
			event.preventDefault(); // Prevent browser's default save dialog
			try {
				const response = await fetch('http://localhost:8080/write', {
					method: 'POST',
					headers: {
						'Content-Type': 'application/json'
					},
					body: JSON.stringify({
						path: file, // Send filename
						code: value // Send editor content
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

	onMount(() => {
		window.addEventListener('keydown', handleKeyDown);
		return () => {
			window.removeEventListener('keydown', handleKeyDown);
		};
	});
</script>

<form>
	<input
		type="text"
		bind:value={file}
		class="w-[100%] border border-purple-300 text-center outline-none"
		placeholder="Enter filename"
	/>
</form>

<CodeMirror
	bind:value
	theme={selectedTheme}
	styles={{
		'&': {
			width: '100%',
			height: '95.7vh'
		}
	}}
/>
