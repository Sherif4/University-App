

Directory="Backup/"
if [ ! -d "$Directory" ]; then
	mkdir -p "$Directory"
    echo "dir has been made"
fi

Logs_Dir="logs/"

if [ ! -d "$Logs_Dir" ]; then
	mkdir -p "$Logs_Dir"
    echo "log dir has been made"
fi

logs="logs/University_log.log"

log_line(){
	local type="$1"
	local message="$2"
	echo "$(date '+%Y-%m-%d %H:%M:%S') [$type] $message" >> "$logs"
}

Current_time=$(date +%Y%m%d_%H%M%S)

backup="University_${Current_time}.dmp"
if exp UNIVERSITY/123@XE FILE="${Directory}/${backup}" statistics=none; 
then
    log_line "Backup INFO" "Backup was done successfully in: ${directory}/${backup}"
    echo "Backup has done successfully"
    
else
    log_line "Backup Warning" "Backup has failed"
    echo "backup failed"
fi

disk_usage=$(df -h | awk 'NR == 3 {sub(/%/, "", $5); print $5}')
warning_usage=75

if [ "$disk_usage" -lt "$warning_usage" ]; then
    log_line "Disk INFO" "Disk usage is: $disk_usage, which still within safe zone."
else
    log_line "Disk Warning" "Disk usage is high: $disk_usage, please free up space as soon as possible."
fi

