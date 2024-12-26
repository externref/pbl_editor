<script lang="ts">
    import { page } from '$app/stores';
    import { goto } from '$app/navigation';
    let filename = ''; 
</script>

<div class="home-container flex flex-col items-center justify-center  py-6 bg-gray-100">
    <!-- Welcome Section -->
    <h1 class="text-4xl font-bold text-purple-600 py-6">Welcome to Wisp</h1>
    <p class="text-lg text-gray-700 text-center max-w-xl">
        This project is a web-based code editor built using modern frameworks and tools like SvelteKit, CodeMirror, and TailwindCSS. 
        It supports syntax highlighting, autocompletion, and theming, making it a versatile tool for editing code files directly in the browser.
    </p>

        <!-- File Input Section -->
        <div class="file-input flex items-center py-5 space-x-4">
            <input
                type="text"
                placeholder="Enter file name or path"
                bind:value={filename}
                class="border border-gray-300 rounded-lg px-4 py-2 w-80 outline-none focus:ring-2 focus:ring-purple-300"
            />
            <button
                on:click={() => goto(`/editor/${encodeURIComponent(filename)}`)}
                class="bg-purple-600 text-white px-6 py-2 rounded-lg hover:bg-purple-500 transition"
            >
                Open File
            </button>
        </div>

    <!-- Project Details Section -->
    <div class="details bg-white border border-gray-300 rounded-lg p-6 shadow-md max-w-xl text-gray-600">
        <h2 class="text-xl font-semibold mb-2">About the Project</h2>
        <p>
            This code editor is designed to provide a simple, lightweight, and effective editing experience for developers. 
            It includes the following features:
        </p>
        <ul class="list-disc list-inside mt-3">
            <li>Support for multiple programming languages, including JavaScript, Python, HTML, CSS, and more.</li>
            <li>Automatic syntax highlighting based on file type.</li>
            <li>Theme customization with a variety of built-in themes.</li>
            <li>File read and write functionality integrated with a Spring Boot backend.</li>
            <li>Keyboard shortcuts, such as <kbd>Ctrl</kbd> + <kbd>S</kbd> to save files.</li>
        </ul>
    </div>



    <!-- Instructions Section -->
    <div class="instructions bg-white border border-gray-300 rounded-lg p-6 shadow-md max-w-xl text-gray-600">
        <h2 class="text-xl font-semibold mb-2">Instructions</h2>
        <ul class="list-disc list-inside">
            <li>Enter the file name or path in the input box above.</li>
            <li>Click "Open File" to start editing.</li>
            <li>Press <kbd>Ctrl</kbd> + <kbd>S</kbd> to save your changes.</li>
            <li>Choose your preferred theme from the editor's dropdown menu.</li>
        </ul>
    </div>
</div>

<style>
    kbd {
        background: #f3f4f6;
        border: 1px solid #d1d5db;
        border-radius: 3px;
        padding: 2px 4px;
        font-size: 0.875rem;
        font-weight: 500;
    }
</style>
