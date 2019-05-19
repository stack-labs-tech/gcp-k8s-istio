const bs = require('browser-sync').create();
const { exec } = require('child_process');

bs.init({
    ui: false,
    open: true,
    ghostMode: false,
    server: {
        baseDir: 'dist',
    },
    watchEvents: ['add', 'change', 'unlink', 'addDir', 'unlinkDir'],
    files: [
        {
            match: ['./**/*'],
            fn: function(event, file) {
                console.log('[\x1b[33mAntora\x1b[0m] Rebuilding site...');
                exec('yarn build', { encoding: 'utf8' }, (error, stdout, stderr) => {
                    if (error) {
                        console.error(error);
                    }
                    if (stderr) {
                        console.error(stderr);
                    }
                    console.log(stdout);
                    bs.reload();
                });
            }
        }
    ]
});
